package kr.ac.jejunu.myboard.service;

import kr.ac.jejunu.myboard.dto.MemoDto;
import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import kr.ac.jejunu.myboard.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<MemoDto> getMemolist(Integer pageNum) { // 메모 조회, getMemoList
        //Pagable 인터페이스 구현한 클래스 PageRequest of 
        Page<MemoEntity> page = memoRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate"))); //repository find할때 가져옴
        // PageRequest.of(limit , offset 몇 개 가져올거, createDate 컬럼을 기준으로 오름차순)
        
        List<MemoEntity> memoEntities = page.getContent();
        List<MemoDto> memoDtoList = new ArrayList<>();

        for ( MemoEntity memoEntity : memoEntities) {
            memoDtoList.add(this.convertEntityToDto(memoEntity));
        }

        return memoDtoList;
    }

    @Transactional
    public MemoDto getPost(Long id) { // 메모 자세히 보기 기능
        Optional<MemoEntity> memoEntityWrapper = memoRepository.findById(id); // PK값을 where 조건으로, JpaRepository 인터페이스 있음
        MemoEntity memoEntity = memoEntityWrapper.get(); // 반환 값은이 Optional 타입일 때, 엔티티를 빼오려면 이렇게 get() 메소드 사용

        return this.convertEntityToDto(memoEntity);
    }

    @Transactional
    public void deletePost(Long id) { // 삭제 기능
        memoRepository.deleteById(id); // PK 값을 where 조건으로 데이터를 삭제하기 위한 메서드, JpaRepository 인터페이스에 정의되어 있음
    }

    @Transactional
    public List<MemoDto> searchPosts(String keyword) { // Repository 검색 결과 받아와서 수행, Dto 객체 전달
        List<MemoEntity> memoEntities = memoRepository.findByTitleContaining(keyword);
        List<MemoDto> memoDtoList = new ArrayList<>();

        if (memoEntities.isEmpty()) return memoDtoList;

        for (MemoEntity memoEntity : memoEntities) {
            memoDtoList.add(this.convertEntityToDto(memoEntity));
        }

        return memoDtoList;
    }

    @Transactional
    public List<MemoDto> searchTagPosts(String keyword) { // Repository 검색 결과 받아와서 수행, Dto 객체 전달
        List<MemoEntity> memoEntities = memoRepository.findByTagContaining(keyword);
        List<MemoDto> memoDtoList = new ArrayList<>();

        if (memoEntities.isEmpty()) return memoDtoList;

        for (MemoEntity memoEntity : memoEntities) {
            memoDtoList.add(this.convertEntityToDto(memoEntity));
        }

        return memoDtoList;
    }

    private static final int BLOCK_PAGE_NUM_COUNT = 10;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 10;       // 한 페이지에 존재하는 메모 수



    @Transactional
    public Long getMemoCount() { // 전체 메모 개수 가져왓
        return memoRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) { // 표시될 페이지 번호 계산
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 메모 갯수
        Double postsTotalCount = Double.valueOf(this.getMemoCount());

        // 총 메모 중 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 메모 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    private MemoDto convertEntityToDto(MemoEntity memoEntity) { //  Entity를 Dto로 변환하는 작업 함수화
        return MemoDto.builder()
                .id(memoEntity.getId())
                .title(memoEntity.getTitle())
                .tag(memoEntity.getTag())
                .content(memoEntity.getContent())
                .createdDate(memoEntity.getCreatedDate())
                .build();
    }

}
