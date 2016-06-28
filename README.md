# SpringTransactionProgrammatic
Using Programmatic Approach

<hr>
<b>Spring Transaction Management Using Programmatic</b><br><br>

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
		
