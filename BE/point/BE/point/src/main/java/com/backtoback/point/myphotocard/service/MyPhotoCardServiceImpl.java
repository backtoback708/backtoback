package com.backtoback.point.myphotocard.service;

import com.backtoback.point.member.domain.Member;
import com.backtoback.point.member.service.MemberService;
import com.backtoback.point.myphotocard.domain.MyPhotoCard;
import com.backtoback.point.myphotocard.repository.MyPhotoCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPhotoCardServiceImpl implements MyPhotoCardService{

    private final MemberService memberService;
    private final MyPhotoCardRepository myphotocardRepository;
    @Override
    @Transactional
    public void createMyPhotocard(MyPhotoCard myPhotoCard){
        myphotocardRepository.save(myPhotoCard);
    }

    @Override
    @Transactional
    public List<MyPhotoCard> getMyPhotocardList(Long memberSeq){
        Member member = memberService.getMember(memberSeq);
        System.out.println(member);
        return myphotocardRepository.findByMember(member);
    }
}
