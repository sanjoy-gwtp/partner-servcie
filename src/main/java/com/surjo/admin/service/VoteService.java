package com.surjo.admin.service;


import com.surjo.admin.model.Vote;

import java.util.List;

public interface VoteService {

    Vote createVote(Vote vote);

    List<Vote> getAllVote();

    Vote getVoteById(Long id);

    void deleteVote(Long id);
}
