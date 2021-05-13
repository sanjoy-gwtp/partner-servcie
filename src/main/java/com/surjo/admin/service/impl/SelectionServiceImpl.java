package com.surjo.admin.service.impl;

import com.surjo.admin.entity.SelectionEntity;
import com.surjo.admin.entity.VoteEntity;
import com.surjo.admin.model.Selection;
import com.surjo.admin.model.Vote;
import com.surjo.admin.repository.SelectionRepository;
import com.surjo.admin.repository.VoteRepository;
import com.surjo.admin.service.SelectionService;
import com.surjo.security.core.CustomSession;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectionServiceImpl implements SelectionService {

    Logger logger = LoggerFactory.getLogger(SelectionService.class);

    private final SelectionRepository selectionRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;
    private final CustomSession session;

    public SelectionServiceImpl(SelectionRepository selectionRepository, VoteRepository voteRepository, ModelMapper modelMapper, CustomSession session) {
        this.selectionRepository = selectionRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
        this.session = session;
    }


    @Override
    public Selection createSelection(Selection selection) {
        SelectionEntity selectionEntity=selectionRepository.save(modelMapper.map(selection,SelectionEntity.class));
        return modelMapper.map(selectionEntity,Selection.class);
    }

    @Override
    public List<Selection> getAllSelection() {
        return selectionRepository.findAll().stream().map(selectionEntity -> modelMapper.map(selectionEntity,Selection.class)).collect(Collectors.toList());
    }

    @Override
    public Selection getSelectionById(Long id) {
        return modelMapper.map(selectionRepository.getOne(id),Selection.class);
    }

    @Override
    public void deleteSelection(Long id) {
        selectionRepository.delete(selectionRepository.getOne(id));
    }

    @Override
    public Selection getSelectionByVoteId(Long voteId) {
        SelectionEntity selectionEntity=selectionRepository.findByUserIdAndVote(session.getUsername(),voteRepository.getOne(voteId));
        if(selectionEntity!=null) {
            return modelMapper.map(selectionEntity, Selection.class);
        }else {
            return null;
        }
    }

    @Override
    public List<Selection> getAllSelectionByUser() {
        List<Selection> selections=new ArrayList<>();
        for (VoteEntity voteEntity:voteRepository.findAll()){
            SelectionEntity selectionEntity=selectionRepository.findByUserIdAndVote(session.getUsername(),voteEntity);
            if(selectionEntity!=null) {
                selections.add(modelMapper.map(selectionEntity,Selection.class));
            }else {
                Selection selection=new Selection();
                selection.setVote(modelMapper.map(voteEntity,Vote.class));
                selection.setUserId(session.getUsername());
                logger.info("Selection:'{}'",selection.getVote().getId());
                selections.add(selection);
            }
        }
        return selections;
    }
}
