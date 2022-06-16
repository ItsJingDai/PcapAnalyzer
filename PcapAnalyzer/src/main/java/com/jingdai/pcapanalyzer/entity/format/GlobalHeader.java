package com.jingdai.pcapanalyzer.entity.format;

/**
 * GlobalHeader pcap header
 */
public class GlobalHeader {

	public static final int LINK_TYPE_ETHERNET = 1;

	public static final int ENDIAN_TYPE_UNKNOWN = 0;
	public static final int ENDIAN_TYPE_BIG = 1;
	public static final int ENDIAN_TYPE_LITTLE = 2;

	private int magic;
	private int linkType;
	
	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

	public GlobalHeader() {}

	@Override
	public String toString() {
		return "GlobalHeader{" +
				"magic=" + magic +
				", linkType=" + linkType +
				'}';
	}

	public static boolean isLittleEndian(int magic) {
		if (magic == 0xd4c3b2a1) {
			return true;
		}
		return false;
	}
}
