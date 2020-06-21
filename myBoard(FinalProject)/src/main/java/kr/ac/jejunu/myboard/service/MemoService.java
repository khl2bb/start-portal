package kr.ac.jejunu.myboard.service;

import kr.ac.jejunu.myboard.dto.MemoDto;
import kr.ac.jejunu.myboard.entitiy.MemoEntity;
import kr.ac.jejunu.myboard.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor // Repository 주입을 위해
@Service // 서비스 계층 명시
public class MemoService {
    private MemoRepository memoRepository;

    @Transactional //선언적 트랜젝션, 트랜잭션 적용
    public Long savePost(MemoDto boardDto) {
        return memoRepository.save(boardDto.toEntity()).getId();
        //repository.save()는 JpaRepository에 정의된 매소드, DB에 INSERT, UPDATE를 담당, 매개변수로 Entity 전달
    }
    
}
