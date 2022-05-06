package com.springheroes.wellco.services;


import com.springheroes.wellco.entities.CI;
import com.springheroes.wellco.entities.User;
import com.springheroes.wellco.enumeration.CentreInter;
import com.springheroes.wellco.repositories.CIRepo;
import com.springheroes.wellco.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService{
    @Autowired
	UserRepository userRepository;
    @Autowired
	CIRepo ciRepo;


	@Override
	public void setCentreInterest(Long idUser, CentreInter centreInter) {
		User u = userRepository.findById(idUser).get();
		CI ci = new CI();
		ci.setCentreInterest(centreInter.toString());
		ci.setUser(u);
		ciRepo.save(ci);
		userRepository.save(u);
		
	}
}
