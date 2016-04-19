/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.cognitoidentityprovider.model;

import java.io.Serializable;

/**
 * <p>
 * The type of constraints associated with an attribute of the string
 * type.
 * </p>
 */
public class StringAttributeConstraintsType implements Serializable {

    /**
     * The minimum length of an attribute value of the string type.
     */
    private String minLength;

    /**
     * The maximum length of an attribute value of the string type.
     */
    private String maxLength;

    /**
     * The minimum length of an attribute value of the string type.
     *
     * @return The minimum length of an attribute value of the string type.
     */
    public String getMinLength() {
        return minLength;
    }
    
    /**
     * The minimum length of an attribute value of the string type.
     *
     * @param minLength The minimum length of an attribute value of the string type.
     */
    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }
    
    /**
     * The minimum length of an attribute value of the string type.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param minLength The minimum length of an attribute value of the string type.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public StringAttributeConstraintsType withMinLength(String minLength) {
        this.minLength = minLength;
        return this;
    }

    /**
     * The maximum length of an attribute value of the string type.
     *
     * @return The maximum length of an attribute value of the string type.
     */
    public String getMaxLength() {
        return maxLength;
    }
    
    /**
     * The maximum length of an attribute value of the string type.
     *
     * @param maxLength The maximum length of an attribute value of the string type.
     */
    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
    
    /**
     * The maximum length of an attribute value of the string type.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param maxLength The maximum length of an attribute value of the string type.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public StringAttributeConstraintsType withMaxLength(String maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMinLength() != null) sb.append("MinLength: " + getMinLength() + ",");
        if (getMaxLength() != null) sb.append("MaxLength: " + getMaxLength() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getMinLength() == null) ? 0 : getMinLength().hashCode()); 
        hashCode = prime * hashCode + ((getMaxLength() == null) ? 0 : getMaxLength().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof StringAttributeConstraintsType == false) return false;
        StringAttributeConstraintsType other = (StringAttributeConstraintsType)obj;
        
        if (other.getMinLength() == null ^ this.getMinLength() == null) return false;
        if (other.getMinLength() != null && other.getMinLength().equals(this.getMinLength()) == false) return false; 
        if (other.getMaxLength() == null ^ this.getMaxLength() == null) return false;
        if (other.getMaxLength() != null && other.getMaxLength().equals(this.getMaxLength()) == false) return false; 
        return true;
    }
    
}
    