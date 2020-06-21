package kr.ac.jejunu.myboard.dto;

import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemoDto {
    private Long id;
    private String title;
    private String tag;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemoEntity toEntity(){
        MemoEntity memoEntity = MemoEntity.builder()
                .id(id)
                .title(title)
                .tag(tag)
                .content(content)
                .build();
        return memoEntity;
    }

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
