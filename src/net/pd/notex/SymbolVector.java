package net.pd.notex;

public class SymbolVector extends Symbol {
	
	public float[][][] paths;
	public boolean stretched = false;
	
	public SymbolVector(String name) {
		//https://www.desmos.com/geometry to make the symbols (write down the point positions into a table)
		this.type = Symbol.VECTOR;
		//anti java developer design
		switch(name) {
			case "(":
				this.paths = new float[][][] {
					{
						{-0f,0f},
						{-5f,1f},
						{-9f,3f},
						{-13f,7f},
						{-15f,11f},
						{-16f,16f},
						{-15f,21f},
						{-13f,25f},
						{-9f,29f},
						{-5f,31f},
						{-0f,32f}
					}
				};
				this.width = 0;
				this.height = 32;
				this.stretched = true;
				break;
			case ")":
				this.paths = new float[][][] {
					{
						{0f,0f},
						{5f,1f},
						{9f,3f},
						{13f,7f},
						{15f,11f},
						{16f,16f},
						{15f,21f},
						{13f,25f},
						{9f,29f},
						{5f,31f},
						{0f,32f}
					}
				};
				this.width = 0;
				this.height = 32;
				this.stretched = true;
				break;
			case "<":
				this.paths = new float[][][] {
					{
						{0f,0f},
						{-16f,16f},
						{0f,32f}
					}
				};
				this.width = 0;
				this.height = 32;
				this.stretched = true;
				break;
			case ">":
				this.paths = new float[][][] {
					{
						{0f,0f},
						{16f,16f},
						{0f,32f}
					}
				};
				this.width = 0;
				this.height = 32;
				this.stretched = true;
				break;
			case "[":
				this.paths = new float[][][] {
					{
						{0f,0f},
						{-16f,0f},
						{-16f,32f},
						{0f,32f}
					}
				};
				this.width = 0;
				this.height = 32;
				this.stretched = true;
				break;
			case "]":
				this.paths = new float[][][] {
					{
						{0f,0f},
						{16f,0f},
						{16f,32f},
						{0f,32f}
					}
				};
				this.width = 0;
				this.height = 32;
				this.stretched = true;
				break;
			case "times":
				this.paths = new float[][][] {
					{
						{4f,5f},
						{6f,6f},
						{7f,8f},
						{6f,10f},
						{4f,11f},
						{2f,10f},
						{1f,8f},
						{2f,6f},
						{4f,5f}
					}
				};
				break;
			case "question":
				this.paths = new float[][][] {
					{
						{0f,4f},
						{1f,1f},
						{4f,0f},
						{7f,1f},
						{8f,4f},
						{7f,7f},
						{5f,9f},
						{4f,11f},
						{4f,13f}
					},
					{
						{4f,15f},
						{4f,16f}
					}
				};
				break;
			case "colon":
				this.paths = new float[][][] {
					{
						{4f,0f},
						{4f,1f}
					},
					{
						{4f,15f},
						{4f,16f}
					}
				};
				break;
			case "theta":
				this.paths = new float[][][] {
					{
						{0f,8f},
						{1f,3f},
						{2f,1f},
						{4f,0f},
						{6f,1f},
						{7f,3f},
						{8f,8f},
						{7f,13f},
						{6f,15f},
						{4f,16f},
						{2f,15f},
						{1f,13f},
						{0f,8f}
					},
					{
						{0f,8f},
						{8f,8f}
					}
				};
				break;
			case "logo":
				this.paths = new float[][][] {
					{ //boundaries
						{0f,0f},
						{0f,16f},
						{16f,16f},
						{16f,0f},
						{0f,0f}
					},
					{//iteration 1
						{8f,0f},
						{8f,16f}
					},
					{
						{0f,8f},
						{16f,8f}
					},
					{//iteration 2
						{4f,0f},
						{4f,8f}
					},
					{
						{0f,4f},
						{8f,4f}
					},
					{
						{12f,8f},
						{12f,16f}
					},
					{
						{8f,12f},
						{16f,12f}
					},
					{//iteration 3
						{2f,0f},
						{2f,4f}
					},
					{
						{0f,2f},
						{4f,2f}
					},
					{
						{6f,4f},
						{6f,8f}
					},
					{
						{4f,6f},
						{8f,6f}
					},
					{
						{10f,8f},
						{10f,12f}
					},
					{
						{8f,10f},
						{12f,10f}
					},
					{
						{14f,12f},
						{14f,16f}
					},
					{
						{12f,14f},
						{16f,14f}
					}
				};
				
				this.width = 16;
				break;
			case "root":
				this.paths = new float[][][] {
					{
						{-16f,16f},
						{-8f,16f},
						{0f,32f},
						{16f,0f}
					}
				};
				
				this.width = 16;
				this.height = 32;
				this.stretched = true;
				break;
			default:
				//unknown character
				this.paths = new float[][][] {
					{
						{0f,4f},
						{1f,1f},
						{4f,0f},
						{7f,1f},
						{8f,4f},
						{7f,7f},
						{5f,9f},
						{4f,11f},
						{4f,13f}
					},
					{
						{4f,15f},
						{4f,16f}
					},
					{
						{-1f,-1f},
						{9f,-1f},
						{9f,17f},
						{-1f,17f},
						{-1f,-1f}
					}
				};
				break;
		}
	}
}
