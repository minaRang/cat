package com.backendStudy.cat;

import com.backendStudy.cat.domain.DTOTag;
import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void testInsert(){
        DTOTag tag = new DTOTag();
        tag.setTagName("Java");
        tag.setBoardIdx(1);
        tag.setUserIdx(Long.valueOf(1));

        tagMapper.insertTag(tag);
        DTOTag result= tagMapper.findByTagIdx(tag.getTagIdx()).get();
        assertThat(result.getTagIdx()).isEqualTo(tag.getTagIdx());
    }

    @Test
    public void testUpdate(){

        DTOTag tag = new DTOTag();
        tag.setTagName("Java Script");
        tag.setUserIdx(Long.valueOf(1));
        tag.setBoardIdx(1);
        tagMapper.insertTag(tag);
        log.info("before update tag index {}",tag.getTagIdx());
        log.info("tag Idx : {}",tag.getBoardIdx());
        log.info("name : {}",tag.getTagName());
        log.info("user : index={}",tag.getUserIdx());

        DTOTag tag2 = new DTOTag();
        tag2.setTagName("Json");
        tag2.setUserIdx(Long.valueOf(2));
        tag2.setTagIdx((long) tag.getTagIdx());

        int cnt =tagMapper.updateTag(tag2);

        DTOTag result=tagMapper.findByTagIdx(tag.getTagIdx()).get();
        log.info("after update tag index : {}",result.getTagIdx());
        log.info("tag Idx : {}",result.getTagIdx());
        log.info("name : {}",result.getTagName());
        log.info("user : index={}",result.getUserIdx());

        assertThat(result.getTagName()).isEqualTo(tag2.getTagName());
        assertThat(cnt).isEqualTo(1);
        assertThat(result.getTagIdx()).isEqualTo(tag.getTagIdx());
    }

    @Test
    public void testDelete(){
        DTOTag tag = new DTOTag();
        tag.setTagName("C");
        tag.setUserIdx(Long.valueOf(1));
        tag.setBoardIdx(2);
        tagMapper.insertTag(tag);

        int result = tagMapper.deleteTag(tag.getTagIdx());
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testFindAllMatchinTag(){
        List<DTOTag> tagList = tagMapper.findAllMatchingTag("C");
        log.info("total={}", tagList.size());
        for (DTOTag tag :tagList) {
            log.info("boardIdx : {}", tag.getBoardIdx());
            log.info("content: {}", tag.getTagName());
        }
    }
}
