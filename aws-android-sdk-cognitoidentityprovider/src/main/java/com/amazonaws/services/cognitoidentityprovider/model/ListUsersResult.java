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
 * The response from the request to list users.
 * </p>
 */
public class ListUsersResult implements Serializable {

    /**
     * The users returned in the request to list users.
     */
    private com.amazonaws.internal.ListWithAutoConstructFlag<UserType> users;

    /**
     * An identifier that was returned from the previous call to this
     * operation, which can be used to return the next set of items in the
     * list.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     */
    private String paginationToken;

    /**
     * The users returned in the request to list users.
     *
     * @return The users returned in the request to list users.
     */
    public java.util.List<UserType> getUsers() {
        if (users == null) {
              users = new com.amazonaws.internal.ListWithAutoConstructFlag<UserType>();
              users.setAutoConstruct(true);
        }
        return users;
    }
    
    /**
     * The users returned in the request to list users.
     *
     * @param users The users returned in the request to list users.
     */
    public void setUsers(java.util.Collection<UserType> users) {
        if (users == null) {
            this.users = null;
            return;
        }
        com.amazonaws.internal.ListWithAutoConstructFlag<UserType> usersCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<UserType>(users.size());
        usersCopy.addAll(users);
        this.users = usersCopy;
    }
    
    /**
     * The users returned in the request to list users.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param users The users returned in the request to list users.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public ListUsersResult withUsers(UserType... users) {
        if (getUsers() == null) setUsers(new java.util.ArrayList<UserType>(users.length));
        for (UserType value : users) {
            getUsers().add(value);
        }
        return this;
    }
    
    /**
     * The users returned in the request to list users.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param users The users returned in the request to list users.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public ListUsersResult withUsers(java.util.Collection<UserType> users) {
        if (users == null) {
            this.users = null;
        } else {
            com.amazonaws.internal.ListWithAutoConstructFlag<UserType> usersCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<UserType>(users.size());
            usersCopy.addAll(users);
            this.users = usersCopy;
        }

        return this;
    }

    /**
     * An identifier that was returned from the previous call to this
     * operation, which can be used to return the next set of items in the
     * list.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @return An identifier that was returned from the previous call to this
     *         operation, which can be used to return the next set of items in the
     *         list.
     */
    public String getPaginationToken() {
        return paginationToken;
    }
    
    /**
     * An identifier that was returned from the previous call to this
     * operation, which can be used to return the next set of items in the
     * list.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param paginationToken An identifier that was returned from the previous call to this
     *         operation, which can be used to return the next set of items in the
     *         list.
     */
    public void setPaginationToken(String paginationToken) {
        this.paginationToken = paginationToken;
    }
    
    /**
     * An identifier that was returned from the previous call to this
     * operation, which can be used to return the next set of items in the
     * list.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param paginationToken An identifier that was returned from the previous call to this
     *         operation, which can be used to return the next set of items in the
     *         list.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public ListUsersResult withPaginationToken(String paginationToken) {
        this.paginationToken = paginationToken;
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
        if (getUsers() != null) sb.append("Users: " + getUsers() + ",");
        if (getPaginationToken() != null) sb.append("PaginationToken: " + getPaginationToken() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getUsers() == null) ? 0 : getUsers().hashCode()); 
        hashCode = prime * hashCode + ((getPaginationToken() == null) ? 0 : getPaginationToken().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof ListUsersResult == false) return false;
        ListUsersResult other = (ListUsersResult)obj;
        
        if (other.getUsers() == null ^ this.getUsers() == null) return false;
        if (other.getUsers() != null && other.getUsers().equals(this.getUsers()) == false) return false; 
        if (other.getPaginationToken() == null ^ this.getPaginationToken() == null) return false;
        if (other.getPaginationToken() != null && other.getPaginationToken().equals(this.getPaginationToken()) == false) return false; 
        return true;
    }
    
}
    