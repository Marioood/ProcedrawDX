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
			default:
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
