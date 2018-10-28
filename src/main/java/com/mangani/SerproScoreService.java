package com.mangani;

import java.math.BigDecimal;

public interface SerproScoreService extends ScoreService<String, BigDecimal> {

    BigDecimal biometricScore(String data);
    BigDecimal biographicScore(String data);
}
