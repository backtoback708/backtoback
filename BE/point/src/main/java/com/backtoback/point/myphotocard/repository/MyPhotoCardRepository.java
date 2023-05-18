package com.backtoback.point.myphotocard.repository;

import com.backtoback.point.member.domain.Member;
import com.backtoback.point.myphotocard.domain.MyPhotoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyPhotoCardRepository extends JpaRepository<MyPhotoCard, Long>{
    List<MyPhotoCard> findByMember(Member member);
}

