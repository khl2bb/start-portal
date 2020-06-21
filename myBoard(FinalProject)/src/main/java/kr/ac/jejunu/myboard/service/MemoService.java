package kr.ac.jejunu.myboard.service;

import kr.ac.jejunu.myboard.dto.MemoDto;
import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import kr.ac.jejunu.myboard.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor // Repository 주입을 위해
@Service // 서비스 계층 명시
public class MemoService {
    private MemoRepository memoRepository;

    @Transactional //선언적 트랜젝션, 트랜잭션 적용
    public Long savePost(MemoDto boardDto) {
        return memoRepository.save(boardDto.toEntity()).getId();
        //repository.save()는 JpaRepository에 정의된 매소드, DB에 INSERT, UPDATE를 담당, 매개변수로 Entity 전달
    }

    @Transactional
    public List<MemoDto> getMemolist() { // 메모 조회, getMemoList
        List<MemoEntity> memoEntities = memoRepository.findAll();
        List<MemoDto> memoDtoList = new ArrayList<>();

        for ( MemoEntity memoEntity : memoEntities) {
            MemoDto memoDTO = MemoDto.builder()
                    .id(memoEntity.getId())
                    .title(memoEntity.getTitle())
                    .tag(memoEntity.getTag())
                    .content(memoEntity.getContent())
                    .createdDate(memoEntity.getCreatedDate())
                    .build();

            memoDtoList.add(memoDTO);
        }

        return memoDtoList;
    }

    @Transactional
    public MemoDto getPost(Long id) { // 메모 자세히 보기 기능
        Optional<MemoEntity> memoEntityWrapper = memoRepository.findById(id); // PK값을 where 조건으로, JpaRepository 인터페이스 있음
        MemoEntity memoEntity = memoEntityWrapper.get(); // 반환 값은이 Optional 타입일 때, 엔티티를 빼오려면 이렇게 get() 메소드 사용

        MemoDto memoDTO = MemoDto.builder()
                .id(memoEntity.getId())
                .title(memoEntity.getTitle())
                .tag(memoEntity.getTag())
                .content(memoEntity.getContent())
                .createdDate(memoEntity.getCreatedDate())
                .build();

        return memoDTO;
    }

    @Transactional
    public void deletePost(Long id) { // 삭제 기능
        memoRepository.deleteById(id); // PK 값을 where 조건으로 데이터를 삭제하기 위한 메서드, JpaRepository 인터페이스에 정의되어 있음
    }


}
