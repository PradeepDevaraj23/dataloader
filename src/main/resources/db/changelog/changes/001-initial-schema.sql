
CREATE TABLE company_data (
  id SERIAL PRIMARY KEY,
  symbol VARCHAR(255) UNIQUE NOT NULL,
  name_of_company VARCHAR(255) NOT NULL,
  series VARCHAR(255) NOT NULL,
  date_of_listing DATE NOT NULL,
  paid_up_value INTEGER NOT NULL,
  market_lot INTEGER NOT NULL,
  isin_number VARCHAR(255) NOT NULL,
  face_value INTEGER NOT NULL,
  CONSTRAINT equity_sequence UNIQUE (symbol)
);

