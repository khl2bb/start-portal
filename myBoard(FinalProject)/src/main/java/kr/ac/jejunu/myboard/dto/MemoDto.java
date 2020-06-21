package kr.ac.jejunu.myboard.dto;

import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemoDto { //  Controller <-> Service <-> Repository 전달 시에 필요한 데이터를 캡슐화한 데이터 전달 객체
    private Long id;
    private String title;
    private String tag;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemoEntity toEntity(){ // toEntity() -- dto에서 필요한 부분을 빌더패턴을 통해 엔티티로 만듬
        MemoEntity memoEntity = MemoEntity.builder()
                .id(id)
                .title(title)
                .tag(tag)
                .content(content)
                .build();
        return memoEntity;
    } // 이런 식으로 필요한 엔티티 추가

    @Builder
    public MemoDto(Long id, String title, String tag, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
