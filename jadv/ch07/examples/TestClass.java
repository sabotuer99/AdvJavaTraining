package examples;

public class TestClass {

		private String hiddenField;
		public String publicField;
		
		public TestClass(){
			
		}
		
		public TestClass(String hiddenField, String publicField){
			this.hiddenField = hiddenField;
			this.publicField = publicField;
		}

		public String getHiddenField() {
			return hiddenField;
		}

		public void setHiddenField(String hiddenField) {
			this.hiddenField = hiddenField;
		}
		
		public String combine(){
			return hiddenField + " " + publicField;
		}
}
