package dsb.web.service;



import dsb.web.domain.SMEAccount;
import dsb.web.domain.TokenPaymentMachine;
import dsb.web.repository.TokenPaymentMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
