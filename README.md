# Spring Transaction Management
Using Programmatic Approach

<hr>
<b>Spring Transaction Management Using Programmatic (PlatformTransactionManager)</b><br><br>

      PlatformTransactionManager platformTxManager;

    	public void setPlatformTxManager(PlatformTransactionManager platformTxManager) {
    		this.platformTxManager = platformTxManager;
    	}
     
     ------------
     -----------
     public void transaction(){
        TransactionDefinition txDef=new DefaultTransactionDefinition();
  		  TransactionStatus status=platformTxManager.getTransaction(txDef);
  		  try {
  			
    			-------
    			platformTxManager.commit(status);
  			
  		  } catch (DataAccessException e) {
  			------
  			platformTxManager.rollback(status);
  			----
  		}
		}
		
<hr>
<b>Spring Transaction Management Using Programmatic (TransactionTemplate)</b><br><br>

	TransactionTemplate transactionTemplate;

	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	-------
	
	public void insert(Account account) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				-----
			}
		});
	}

	
	public void update(Account account) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				-----
			}
		});
	}
<hr>	
<b>Bean Definition</b>

	
	<bean id="myDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
		<property name="url" value="jdbc:mysql://localhost:3306/test"/>
		<property name="username" value="root"/>
		<property name="password" value="root"/>
	</bean>
	
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="myDataSource"/>
	</bean>
	
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="myDataSource"></property>
	</bean>
	<!--Using PlatformTransactionManager -->
	<bean id="accountDao" class="com.swadesibank.transaction.daoImpl.AccountDaoImpl">
		<property name="jdbcTemplate" ref="jdbcTemplate"/> 
		<property name="platformTxManager" ref="transactionManager"/>
	</bean>
	
	<bean id="accountService" class="com.swadesibank.transaction.service.AccountService">
		<property name="accountDao" ref="accountDao"/> 
	</bean>
	
	<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
		<property name="transactionManager" ref="transactionManager"/>
	</bean>
	<!--Using TransactionTemplate -->
	<bean id="accountDaoV2" class="com.swadesibank.transaction.daoImpl.AccountDaoImplV2">
		<property name="jdbcTemplate" ref="jdbcTemplate"/> 
		<property name="transactionTemplate" ref="transactionTemplate"/>
	</bean>
	
	<bean id="accountServiceV2" class="com.swadesibank.transaction.service.AccountServiceV2">
		<property name="accountDao" ref="accountDaoV2"/> 
	</bean>
	
