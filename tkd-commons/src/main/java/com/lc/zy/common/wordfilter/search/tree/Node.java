package com.lc.zy.common.wordfilter.search.tree;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private Map<String, Node> children = new HashMap<String, Node>();
	private boolean isEnd = false;
	private int level = 0;

	public Node addChar(char c) {
		String cStr = String.valueOf(c);
		Node node = (Node) this.children.get(cStr);
		if (node == null) {
			node = new Node();
			this.children.put(cStr, node);
		}
		return node;
	}

	public Node findChar(char c) {
		String cStr = String.valueOf(c);
		return (Node) this.children.get(cStr);
	}

	public boolean isEnd() {
		return this.isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public int getLevel() {
		return this.level;
	}

	//def level = 4
    public void setLevel() {
        this.level = 4;
    }

	public void setLevel(int level) {
		this.level = level;
	}
}