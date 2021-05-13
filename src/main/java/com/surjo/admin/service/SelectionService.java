package com.surjo.admin.service;

import com.surjo.admin.model.Selection;
import com.surjo.admin.model.Type;
import com.surjo.admin.model.Vote;

import java.util.List;

public interface SelectionService {
    Selection createSelection(Selection selection);

    List<Selection> getAllSelection();

    Selection getSelectionById(Long id);

    void deleteSelection(Long id);

    Selection getSelectionByVoteId(Long voteId);

    List<Selection> getAllSelectionByUser();

}
