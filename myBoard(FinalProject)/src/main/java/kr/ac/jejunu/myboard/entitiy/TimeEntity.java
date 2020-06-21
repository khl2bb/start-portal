package kr.ac.jejunu.myboard.entitiy;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 테이블로 맵핑하지 않고, 자식 클래스 엔티티에게 맵핑 정보 상속하기 위해
@EntityListeners(AuditingEntityListener.class) // JPA에게 알리기, 해당 엔티티는 Auditing 기능을 사용한다.
public abstract class TimeEntity {
    @CreatedDate // 엔티티가 처음 저장될 때 생성일 주입 기능
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate // 엔티티가 수정될 때 수정일자 주입 기능
    private LocalDateTime modifiedDate;
}
