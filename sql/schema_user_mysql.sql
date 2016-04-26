SET FOREIGN_KEY_CHECKS = 0;

# ==============================================
# ........... TABLAS ..........
# ==============================================

DROP TABLE IF EXISTS comunidad_autonoma;

CREATE TABLE comunidad_autonoma (
  ca_id  SMALLINT UNSIGNED NOT NULL,
  nombre VARCHAR(100)      NOT NULL,
  PRIMARY KEY (ca_id)
);

SET FOREIGN_KEY_CHECKS = 1;

# ==============================================
# ........... VIEWS ..........
# ==============================================


