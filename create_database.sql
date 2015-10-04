#
# Create Sonar database and user.
#
# Command: mysql -u root -p < create_database.sql
#

CREATE DATABASE sonar CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER '<user>' IDENTIFIED BY '<password>';
GRANT ALL ON sonar.* TO '<user>'@'%' IDENTIFIED BY '<password>';
GRANT ALL ON sonar.* TO '<user>'@'localhost' IDENTIFIED BY '<password>';
FLUSH PRIVILEGES;