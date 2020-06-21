package kr.ac.jejunu.myboard.entitiy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "memo")
public class MemoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String tag;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public MemoEntity(Long id, String title, String tag, String content) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.content = content;
    }
}
