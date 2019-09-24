package com.rpa.web.pojo;

import java.io.Serializable;

/**
 * t_key_text
 * @author 
 */
public class KeyTextPO implements Serializable {
    private Integer keyName;

    private String text;

    private static final long serialVersionUID = 1L;

    public Integer getKeyName() {
        return keyName;
    }

    public void setKeyName(Integer keyName) {
        this.keyName = keyName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        KeyTextPO other = (KeyTextPO) that;
        return (this.getKeyName() == null ? other.getKeyName() == null : this.getKeyName().equals(other.getKeyName()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getKeyName() == null) ? 0 : getKeyName().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", keyName=").append(keyName);
        sb.append(", text=").append(text);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}