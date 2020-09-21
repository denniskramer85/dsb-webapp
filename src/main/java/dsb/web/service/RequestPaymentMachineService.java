package dsb.web.service;



import dsb.web.domain.*;
import dsb.web.repository.TokenPaymentMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RequestPaymentMachineService {

    private TokenPaymentMachineRepository tokenPaymentMachineRepository;



    @Autowired
    public RequestPaymentMachineService(TokenPaymentMachineRepository tokenPaymentMachineRepository) {
        this.tokenPaymentMachineRepository = tokenPaymentMachineRepository;
    }

    //pinautomaat aavragen, token aanmaken
    public TokenPaymentMachine createAndSaveToken(SMEAccount smeAccount) {
        TokenPaymentMachine token = new TokenPaymentMachine(smeAccount);
        tokenPaymentMachineRepository.save(token);
        return token;
    }

    public TokenPaymentMachine generateSecCodeToken(TokenPaymentMachine tokenPaymentMachine) {
        SecureRandom random = new SecureRandom();
        int securityCode = random.nextInt(100000);
        tokenPaymentMachine.setSecurityCode(securityCode);
        return tokenPaymentMachineRepository.save(tokenPaymentMachine);
        //TODO if succesful delete or modify token?
    }


}
