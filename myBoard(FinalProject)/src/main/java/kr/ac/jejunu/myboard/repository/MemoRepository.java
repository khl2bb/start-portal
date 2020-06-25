package kr.ac.jejunu.myboard.repository;

import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 인터페이스, JpaRepository 인터페이스 상속
public interface MemoRepository extends JpaRepository<MemoEntity, Long> { // JpaRepository<엔티티, 프라이머리 키 타입>
    // 검색을 직접적으로 호출
    List<MemoEntity> findByTitleContaining(String keyword); // JpaRepository 메소드, By 이후는 SQl where 조건에 대응됨, Containing -> like 검색
    // %{keyword}%
    // JpaRepository에 일반적으로 사용하는 데이터 조작 함수가 정의되어 있음. CRUD
    List<MemoEntity> findByTagContaining(String tag);
}