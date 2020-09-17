package dsb.web.service;



import dsb.web.domain.SMEAccount;
import dsb.web.domain.TokenPaymentMachine;
import dsb.web.repository.RequestPaymentMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestPaymentMachineService {

    private RequestPaymentMachineRepository requestPaymentMachineRepository;



    @Autowired
    public RequestPaymentMachineService(RequestPaymentMachineRepository requestPaymentMachineRepository) {
        this.requestPaymentMachineRepository = requestPaymentMachineRepository;
    }

    //pinautomaat aavragen, token aanmaken
    public TokenPaymentMachine createAndSaveToken(SMEAccount smeAccount) {
        TokenPaymentMachine token = new TokenPaymentMachine(smeAccount);
        requestPaymentMachineRepository.save(token);
        return token;
    }
}
