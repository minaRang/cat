package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOFond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface FondService {
    Long updateFondBoard(Long boardIdx, Long userIdx, long score);
    public Integer cntFondBoard(Long boardIdx);
    DTOFond findFondBoard(DTOFond fond);

    Long updateFondAnswer(Long answerIdx, Long userIdx, long score);
    Integer cntFondAnswer(Long answerIdx);
    DTOFond findFondAnswer(DTOFond fond);
}
