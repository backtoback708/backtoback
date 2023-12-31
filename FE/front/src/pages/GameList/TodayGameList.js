import { Link } from "react-router-dom";
import "./TodayGameList.css";

const TodayGameList = (props) => {
  const homeImgPath =
    process.env.PUBLIC_URL + "/team/" + props.homeTeamSeq + ".svg";
  const awayImgPath =
    process.env.PUBLIC_URL + "/team/" + props.awayTeamSeq + ".svg";

  return (
    <div>
      <Link to={`/live/${props.gameid}`} className="link-body">
        <div className="grid game-card">
          <div className="flex justify-content-center col-5 home-info">
            <img
              className="team-image"
              src={homeImgPath}
              alt="Home Image"
            ></img>
            <div className="team-name">{props.homeTeamName}</div>
          </div>
          <div className="col-2 game-info">
            <div className="game-time">
              {props.time[0]} 년 {props.time[1]} 월 {props.time[2]} 일
            </div>
            <div className="game-place">{props.place}</div>
          </div>
          <div className="flex justify-content-center col-5 away-info">
            <img
              className="team-image"
              src={awayImgPath}
              alt="Away Image"
            ></img>
            <div className="team-name">{props.awayTeamName}</div>
          </div>
        </div>
      </Link>
    </div>
  );
};

export default TodayGameList;
