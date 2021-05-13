package com.surjo.admin.service.impl;

import com.surjo.admin.entity.VoteEntity;
import com.surjo.admin.model.Type;
import com.surjo.admin.model.Vote;
import com.surjo.admin.repository.VoteRepository;
import com.surjo.admin.service.VoteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;

    public VoteServiceImpl(VoteRepository voteRepository, ModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vote createVote(Vote vote) {
        VoteEntity voteEntity=voteRepository.save(modelMapper.map(vote,VoteEntity.class));
        return modelMapper.map(voteEntity,Vote.class);
    }

    @Override
    public List<Vote> getAllVote() {
        return voteRepository.findAll().stream().map(voteEntity -> modelMapper.map(voteEntity,Vote.class)).collect(Collectors.toList());
    }

    @Override
    public Vote getVoteById(Long id) {
        return modelMapper.map(voteRepository.getOne(id),Vote.class);
    }

    @Override
    public void deleteVote(Long id) {
        voteRepository.delete(voteRepository.getOne(id));
    }
}
