package com.example.codemaster.service;

import com.example.codemaster.config.JwtUtils;
import com.example.codemaster.exception.BusinessException;
import com.example.codemaster.exception.BusinessExceptionCode;
import com.example.codemaster.model.RefreshToken;
import com.example.codemaster.model.User;
import com.example.codemaster.repository.RefreshTokenRepository;
import com.example.codemaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsImplementationService userDetailsService;

    public void deleteRefreshTokenForUser(Long userId) {
        refreshTokenRepository.deleteRefreshTokenFromUser(userId);
    }


    public void createRefreshToken(String uuid, Long userId) {
        RefreshToken rt = new RefreshToken();
        rt.setToken(uuid);
        rt.setExpiryDate(Instant.now().plusSeconds(84000));

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            rt.setUser(user.get());
            refreshTokenRepository.save(rt);
        }
    }


    public String exchangeRefreshToken(String refreshToken) throws BusinessException {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findById(refreshToken);
        if(refreshTokenOptional.isEmpty()) {
            throw new BusinessException(BusinessExceptionCode.INVALID_REFRESH_TOKEN);
        }
        RefreshToken rt = refreshTokenOptional.get();
        if(rt.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(rt);
            throw new BusinessException(BusinessExceptionCode.EXPIRED_REFRESH_TOKEN);
        }
        return jwtUtils.generateJwtToken(userDetailsService.loadUserByUsername(rt.getUser().getUsername()));
    }

}
