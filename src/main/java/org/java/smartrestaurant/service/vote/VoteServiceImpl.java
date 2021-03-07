package org.java.smartrestaurant.service.vote;

import org.java.smartrestaurant.dto.ResultObject;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Vote;
import org.java.smartrestaurant.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@CacheConfig(cacheNames = "votes")
public class VoteServiceImpl implements VoteService {
    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Autowired
    private VoteRepository voteRepository;

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    @Transactional
    public void removeByDate(LocalDate date) {
        logger.info("Remove votes by date");
        voteRepository.removeByDatev(date);
    }

    @Override
    @Cacheable("votes")
    public List<Vote> findVotesByDate(LocalDate date) {
        logger.info("Find votes by date");
        return voteRepository.findVotesByDatev(date);
    }

    @Override
    public boolean existsVotesByDate(LocalDate date) {
        logger.info("Exists votes by day");
        return voteRepository.existsVotesByDatev(date);
    }

    @Override
    public List<ResultObject> getResultByDate(LocalDate date) {
        logger.info("Get voting result by date");
        return voteRepository.getResultByDate(date);
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    @Transactional
    public void removeByDateAndUserId(LocalDate date, int userId) {
        logger.info("Remove votes by date and user Id");
        voteRepository.removeByDateAndUserId(date, userId);
    }

    @Override
    @Cacheable("votes")
    public Vote findVotesByDateAndUserIdAndRestaurantId(LocalDate date, int userId, int restaurantId) {
        logger.info("Find votes by date, userId, restaurantId");
        return voteRepository.findVotesByDateAndUserIdAndRestaurantId(date, userId, restaurantId);
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    @Transactional
    public Vote create(Vote vote) {
        logger.info("Create new vote");
        Objects.requireNonNull(vote, "Parameter vote cannot be null");
        vote.setId(0);
        return voteRepository.save(vote);
    }

    @Override
    @Cacheable("votes")
    public Vote read(int id) throws NotFoundException {
        logger.info("Get vote by id");
        return voteRepository.findById(id);
    }

    @Override
    @Cacheable("votes")
    public List<Vote> readAll() {
        logger.info("Get all votes");
        return voteRepository.findAll();
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    @Transactional
    public Vote update(Vote vote) throws NotFoundException {
        Objects.requireNonNull(vote, "Parameter vote cannot be null");
        logger.info("Update vote with id = {}", vote.getId());
        if (!voteRepository.existsById(vote.getId())) {
            throw new NotFoundException("Vote with id = " + vote.getId() + " not exists");
        }
        return voteRepository.save(vote);
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        logger.info("Update vote with id = {}", id);
        voteRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "votes", allEntries = true)
    @Transactional
    public void deleteAll() {
        logger.info("Delete all votes");
        voteRepository.deleteAll();
    }
}
