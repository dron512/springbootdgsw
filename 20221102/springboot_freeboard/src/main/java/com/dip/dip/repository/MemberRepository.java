package com.dip.dip.repository;

import com.dip.dip.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    // select * from member where email = ?;
    Member findByEmail(String email);

    // select * from member where password = ?;
    Member findByPassword(String password);


}
