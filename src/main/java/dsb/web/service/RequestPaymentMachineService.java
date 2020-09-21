package dsb.web.service;




import dsb.web.controller.AttributeMapping;
import dsb.web.controller.beans.LoginBean;
import dsb.web.controller.beans.TransferBean;
import dsb.web.domain.Customer;
import dsb.web.domain.SMEAccount;
import dsb.web.domain.TokenPaymentMachine;
import dsb.web.repository.TokenPaymentMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.SecureRandom;

@Service
@SessionAttributes(AttributeMapping.LOGGED_IN_CUSTOMER)

public class RequestPaymentMachineService {

    private TokenPaymentMachineRepository tokenPaymentMachineRepository;
    private SignInService signInService;

    @Autowired
    public RequestPaymentMachineService(TokenPaymentMachineRepository tokenPaymentMachineRepository, SignInService signInService) {
        this.tokenPaymentMachineRepository = tokenPaymentMachineRepository;
        this.signInService = signInService;
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
    }





}

