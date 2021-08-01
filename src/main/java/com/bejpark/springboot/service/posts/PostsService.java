package com.bejpark.springboot.service.posts;

import com.bejpark.springboot.domain.posts.Posts;
import com.bejpark.springboot.domain.posts.PostsRepository;
import com.bejpark.springboot.web.dto.PostsListResponseDto;
import com.bejpark.springboot.web.dto.PostsResponseDto;
import com.bejpark.springboot.web.dto.PostsSaveRequestDto;
import com.bejpark.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true) //트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도가 개선됨, 등록 수정 삭제기능이 없는 서비스에서 사용하는 것을 추천
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // posts->new PostsListResponseDto(posts)
                //postRepository 결과로 넘어온 Posts의 Streeam을 map을 통해 PostListResponseDto로 변환, list로 반환하는 메소드
                .collect(Collectors.toList());
    }

}
