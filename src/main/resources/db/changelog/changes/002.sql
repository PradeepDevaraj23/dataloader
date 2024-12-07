CREATE TABLE task_tracker (
  id SERIAL PRIMARY KEY,
  task_name VARCHAR(255) NOT NULL,
  task_status VARCHAR(255) NOT NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  run_by_user VARCHAR(50) NOT NULL
);
