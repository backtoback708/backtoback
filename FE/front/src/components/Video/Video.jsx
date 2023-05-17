import { useEffect } from 'react';
import { useRef,useState } from 'react';
import {useParams} from 'react-router-dom'
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import kurentoUtils from "kurento-utils";
import { v4 as uuidv4 } from 'uuid';

let stomp;
let webRtcPeer;
let sockJs;

const MatchDetail = ({gameSeq}) => {
  const videoRef = useRef(null);
  // const {gameId} = useParams();
  const [readyToStart,setReadyToStart] = useState(false);
  const [readyToVideo,setReadyToVideo] = useState(false);
  const [userId,setUserId] = useState(uuidv4());

  gameSeq = 1;
  useEffect(() => {
     sockJs = new SockJS("http://k8a708.p.ssafy.io/api/media/video");
     stomp = Stomp.over(sockJs);
     enterRoom();
     return () => { 
      stomp.disconnect();
     }
  }, []);

  useEffect(()=>{
    if (readyToStart === true) {
      console.log("start");
        start();
      }
  },[readyToStart]);

  useEffect(()=>{
    if (readyToVideo === true) {
        console.log("start Stream")
        // startStream();
      }
  },[readyToVideo]);

  const enterRoom = () => {
    console.log("enterRoom!!!!!")
    const headers = { "gameId": gameSeq, "userId": userId }
    //2. connection이 맺어지면 실행
    stomp.connect(headers, function () {
      stomp.subscribe(`/user/${userId}/sub/${gameSeq}`, function (message) {
        var parsedMessage = JSON.parse(message.body);
        switch (parsedMessage.id) {
          case "startResponse":
            startResponse(parsedMessage);
            break;
          case "error":
            onError("Error message from server: " + parsedMessage.message);
            break;
          case "playEnd":
            <Redirect to={{ pathname: '/',state: {from: props.location}}}/>
            console.log("playEnd");
            break;
          case "videoInfo":
            startStream();
            setReadyToVideo(true);
            break;
          case "iceCandidate":
            webRtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
              if (error) return console.error("Error adding candidate: " + error);
            });
            break;
          case "seek":
            console.log(parsedMessage.message);
            break;
          case "position":
            document.getElementById("videoPosition").value = parsedMessage.position;
            break;
          default:
            onError("Unrecognized message", parsedMessage);
        }
      });
      // start();
      setReadyToStart(true);
      
    });
  }

  const start = ()=> {
    // Video and audio by default
    var userMediaConstraints = {
      audio: true,
      video: true,
    };
    
    console.log('videoRef current'+videoRef.current);
    var options = {
      video:videoRef.current,
      mediaConstraints: userMediaConstraints,
      onicecandidate: onIceCandidate,
    };
  
    console.info("User media constraints" + userMediaConstraints);
  
    webRtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options, function (error) {
      if (error) return console.error(error);
      webRtcPeer.generateOffer(onOffer);
    });
  
  }
  
  const onOffer = (error, offerSdp) => {
    if (error) return console.error("Error generating the offer");
    console.info("Invoking SDP offer callback function " + "location.host");
  
    var message = {
      id: "start",
      gameId: gameSeq,
      userId: userId,
      sdpOffer: offerSdp,
      // videourl: document.getElementById("videourl").value,
    };
    sendMessage(message);
  }
  
  const onError = (error) => {
    console.error(error);
  }
  
  const onIceCandidate = (candidate) => {
    console.log("Local candidate" + JSON.stringify(candidate));
  
    var message = {
      id: "onIceCandidate",
      gameId: gameSeq,
      userId: userId,
      candidate: candidate,
    };
    sendMessage(message);
  }
  
  const startResponse = (message) => {  
    webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
      if (error) return console.error(error);
      
    });
  }
  
  const startStream = () => {
    console.log("start remoteStream"+webRtcPeer.getRemoteStream)
    videoRef.current.srcObject = webRtcPeer.getRemoteStream();
  }
  
  const sendMessage = function(message){
    var jsonMessage = JSON.stringify(message);
    stomp.send(`/pub/video/${message.id}`, {}, jsonMessage);
  }


  return (<>
    <div>{gameSeq}</div>
     <div>
      <video  poster={ require('images/Video/beforelive.png') } ref={videoRef} autoPlay/>
    </div>
     <button onClick={start}>비디오 시작</button>
    <button onClick={startStream}>비디오 스트림 시작</button>
    <button onClick={enterRoom}>방 입장</button>
  </>)
}

export default MatchDetail;