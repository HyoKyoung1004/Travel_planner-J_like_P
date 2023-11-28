package com.trip.project.service.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.team.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;

	public void teamAdd(int plan_id) {
		teamRepository.teamAdd(plan_id);
	}
	
}
