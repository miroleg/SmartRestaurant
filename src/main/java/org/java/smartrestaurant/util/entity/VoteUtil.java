package org.java.smartrestaurant.util.entity;

import org.java.smartrestaurant.dto.VoteDto;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.model.Vote;
import org.java.smartrestaurant.repository.VoteRepository;
import org.java.smartrestaurant.service.restaurant.RestaurantService;
import org.java.smartrestaurant.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteUtil implements EntityUtil<Vote, VoteDto> {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public VoteDto createDtoFromEntity(Vote vote) {
        return new VoteDto(vote.getId(), vote.getDatev(), vote.getUser().getId(),
                vote.getRestaurant().getId());
    }

    @Override
    public Vote createEntityFromDto(VoteDto voteDto) {
        return new Vote(voteDto.getId(), voteDto.getDatev(),
                userService.read(voteDto.getUser_id()), restaurantService.read(voteDto.getRestaurant_id()));
    }

    @Override
    public Vote updateEntityFromDto(Vote vote, VoteDto voteDto) {
        Restaurant restaurant = restaurantService.read(voteDto.getRestaurant_id());
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    @Override
    public Vote createNewEntityFromAnother(Vote vote) {
        return new Vote(vote.getId(), vote.getDatev(), vote.getUser(), vote.getRestaurant());
    }
}
