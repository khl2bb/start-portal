package kr.ac.jejunu.myboard.entitiy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// javax.persistence 임포트 -> gradle dependency 추가 'org.springframework.boot:spring-boot-starter-data-jpa'

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자 추가, JPA 사용을 위해 기본 생성자 생성 필수
@Getter // 모든 필드 자동 getter 생성,,,,
// setter 대신 builder 사용 ,,, @Data 사용도 선택지
@Entity // 객체와 테이블을 맵핑 할 엔티티 명시, to JPA
@Table(name = "memo") // 엔티티 클래스와 테이블 정보 명시, 테이블 명, default는 엔티티 이름 테이블 명 맵핑
public class MemoEntity extends TimeEntity{

    @Id // 기본 키
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 기본 키를 대체 키로 사용 기본 키 값 생성 전략
    private Long id;

    @Column(length = 100, nullable = false) // 컬럼 맵핑
    private String title;

    @Column(length = 20, nullable = false)
    private String tag;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder // 빌더 패턴 어노테이션
    public MemoEntity(Long id, String title, String tag, String content) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.content = content;
    }
}
