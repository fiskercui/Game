CREATE TABLE t_log_server(
	recordDate DATETIME NOT NULL COMMENT '时间',
	online INT NOT NULL COMMENT '在线人数',
	PRIMARY KEY(recordDate)
) COMMENT '在线人数';

CREATE TABLE t_log_userupgrade(
	id INT NOT NULL AUTO_INCREMENT COMMENT '自增id,保持唯一',
	recordDate DATETIME NOT NULL COMMENT '升级时间',
	userId INT NOT NULL COMMENT '用户id',
	oldLevel SMALLINT NOT NULL COMMENT '原等级',
	newLevel SMALLINT NOT NULL COMMENT '新等级',
	PRIMARY KEY(id),
	KEY ix_recordDate(recordDate),
	KEY ix_userId(userId)
) COMMENT '角色升级记录';

CREATE TABLE t_log_login(
	id INT NOT NULL AUTO_INCREMENT COMMENT '自增id,保持唯一',
	loginDate DATETIME NOT NULL COMMENT '登录时间',
	logoutDate DATETIME NOT NULL COMMENT '登出时间',
	userId INT NOT NULL COMMENT '用户id',
	PRIMARY KEY(id),
	KEY ix_logoutdate(logoutDate),
	KEY ix_userId(userId)
) COMMENT '登录记录';

CREATE TABLE t_log_wealth(
	id INT NOT NULL COMMENT '自增id,保持唯一',
	recordDate DATETIME NOT NULL COMMENT '消费时间',
	userId INT NOT NULL COMMENT '用户id',
	level INT NOT NULL COMMENT '等级',
	mainType SMALLINT NOT NULL COMMENT '消费主类型',
	subType INT NOT NULL COMMENT '消费子类型',
	money INT NOT NULL COMMENT '使用财富,正数为增加,负数为消耗',
	remainMoney INT NOT NULL COMMENT '此次消耗剩余的财富',
	PRIMARY KEY(id),
	KEY ix_recordDate(recordDate),
	KEY ix_userId(userId)
) COMMENT '财富记录';

CREATE TABLE t_log_shop(
	id INT NOT NULL COMMENT '自增id,保持唯一',
	recordDate DATETIME NOT NULL COMMENT '消费时间',
	userId INT NOT NULL COMMENT '付费人用户id',
	level INT NOT NULL COMMENT '付费人等级',
	receiverid INT NOT NULL COMMENT '接收人用户id,用于赠送物品时,其它为0',
	shopType TINYINT(4) NOT NULL COMMENT '商城类型',
	mainType SMALLINT NOT NULL COMMENT '消费主类型',
	subType INT NOT NULL COMMENT '消费子类型',
	moneyType SMALLINT NOT NULL COMMENT '货币类型',
	money INT NOT NULL COMMENT '货币金额',
	templateId INT NOT NULL COMMENT '物品模板id',
	itemId INT NOT NULL COMMENT '物品id',
	itemCount INT NOT NULL COMMENT '物品数量',
	validPeriod INT NOT NULL COMMENT '物品有限期',
	PRIMARY KEY(id),
	KEY ix_recordDate(recordDate),
	KEY ix_userId(userId)
) COMMENT '商城消费记录';


CREATE TABLE t_log_dropitem(
	id INT NOT NULL COMMENT '自增id,保持唯一',
	recordDate DATETIME NOT NULL COMMENT '掉落时间',
	userId INT NOT NULL COMMENT '用户id',
	dropType SMALLINT NOT NULL COMMENT '掉落类型',
	templateId INT NOT NULL COMMENT '物品模板id',
	itemId INT NOT NULL COMMENT '物品id',
	itemCount INT NOT NULL COMMENT '物品数量', 
	isBind TINYINT(1) NOT NULL COMMENT '是否绑定',
	PRIMARY KEY(id),
	KEY ix_recordDate(recordDate),
	KEY ix_userId(userId)
) COMMENT '物品掉落记录';


CREATE TABLE t_log_fight(
	id INT NOT NULL COMMENT '自增id,保持唯一',
	startDate DATETIME NOT NULL COMMENT '开始时间',
	endDate DATETIME NOT NULL COMMENT '结束时间',
	fightType SMALLINT NOT NULL COMMENT '战斗类型',
	mapId INT NOT NULL COMMENT '地图id',
	teamA VARCHAR(2000) NOT NULL COMMENT '组A,逗号拼接的用户id',
	teamB VARCHAR(2000) NOT NULL COMMENT '组B,逗号拼接的用户id',
	teamALevel VARCHAR(2000) NOT NULL COMMENT '组A等级,逗号拼接的角色等级',
	teamBLevel VARCHAR(2000) NOT NULL COMMENT '组B等级,逗号拼接的角色等级',
	result TINYINT(4) NOT NULL COMMENT '战斗结果',
	PRIMARY KEY(id),
	KEY ix_startDate(startDate)
) COMMENT '战斗记录';

CREATE TABLE t_log_item(
	id INT NOT NULL COMMENT '自增id,保持唯一',
	recordDate DATETIME NOT NULL COMMENT '操作时间',
	userId INT NOT NULL COMMENT '用户id',
	operation SMALLINT NOT NULL COMMENT '操作类型',
	templateId INT NOT NULL COMMENT '物品模板id',
	itemId INT NOT NULL COMMENT '物品id', 
	startProperty VARCHAR(1000) NOT NULL COMMENT '操作前属性',
	endProperty VARCHAR(1000) NOT NULL COMMENT '操作后属性',
	isSucceed TINYINT(1) NOT NULL COMMENT '是否成功,1为成功',
	PRIMARY KEY(id),
	KEY ix_recordDate(recordDate),
	KEY ix_userId(userId)
) COMMENT '物品操作记录'; 

CREATE TABLE t_log_vip(
	id INT NOT NULL COMMENT '自增id,保持唯一',
	recordDate DATETIME NOT NULL COMMENT '充值时间',
	userId INT NOT NULL COMMENT '付款人id',
	renewId INT NOT NULL COMMENT '受益人id',
	vipType TINYINT(4) NOT NULL COMMENT 'VIP类型',
	validPeriod SMALLINT NOT NULL COMMENT '续费有效期',
	startDate DATETIME NOT NULL COMMENT '有效期开始时间',
	endDate DATETIME NOT NULL COMMENT '有效期结束时间',
	isFirst TINYINT(1) NOT NULL COMMENT '是否首充,1为首充',
	PRIMARY KEY(id),
	KEY ix_recordDate(recordDate),
	KEY ix_userId(userId)
) COMMENT 'VIP充值记录'; 
