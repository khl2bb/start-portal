package kr.ac.jejunu.myboard.repository;

import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 인터페이스, JpaRepository 인터페이스 상속
public interface MemoRepository extends JpaRepository<MemoEntity, Long> { // JpaRepository<엔티티, 프라이머리 키 타입>
    // JpaRepository에 일반적으로 사용하는 데이터 조작 함수가 정의되어 있음. CRUD
}
