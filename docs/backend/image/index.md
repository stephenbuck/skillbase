**Overview:**

curl -sSL https://d.juicefs.com/install | sh -
juicefs format sqlite3://myjfs.db myjfs
juicefs mount sqlite3://myjfs.db ~/jfs
juicefs umount ~/jfs
