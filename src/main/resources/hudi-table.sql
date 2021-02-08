
-- 在Hive中创建Hudi的COW表
CREATE EXTERNAL TABLE IF NOT EXISTS tbl_hudi_temperature_cow(
    `_hoodie_commit_time` string,
    `_hoodie_commit_seqno` string,
    `_hoodie_record_key` string,
    `_hoodie_partition_path` string,
    `_hoodie_file_name` string,
    `deviceId` string,
    `temperature` double,
    `cdt` string
) PARTITIONED BY (deviceType string)
ROW FORMAT SERDE
    'org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe'
STORED AS INPUTFORMAT
    'org.apache.hudi.hadoop.HoodieParquetInputFormat'
    OUTPUTFORMAT
        'org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat';

-- 在Hive中创建Hudi的MOR表
