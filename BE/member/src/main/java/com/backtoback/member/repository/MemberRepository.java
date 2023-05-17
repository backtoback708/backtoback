package com.backtoback.member.repository;

import com.backtoback.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);


    boolean existsByMemberId(String memberId);

<<<<<<< HEAD
	boolean existsByNickname(String nickname);
=======
>>>>>>> d3e242c9852359e36e08f18e14d7f969c3bdb292
}
