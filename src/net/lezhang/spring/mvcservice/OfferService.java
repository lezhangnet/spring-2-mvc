package net.lezhang.spring.mvcservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lezhang.spring.mvcdao.Offer;
import net.lezhang.spring.mvcdao.OfferDAO;

@Service("offerService")
public class OfferService {
    
    public OfferService() {
        System.out.println("OfferService constructed");
    }
    
    @Autowired
    private OfferDAO offerDao;
    
    public List<Offer> getCurrentOffers() {
        List<Offer> offers = new ArrayList<>();
        
        offers = offerDao.getOffers();
        
        return offers;
    }
}
