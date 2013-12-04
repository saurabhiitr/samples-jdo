/**********************************************************************
Copyright (c) 2006 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package org.datanucleus.samples.jdo.many_many_attributed;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.jdo.identity.LongIdentity;

public class BusinessRelation
{
    private Customer customer;
    private Supplier supplier;
    private String relationLevel;
    private String meetingLocation;

    public BusinessRelation(Customer cust, Supplier supp, String level, String meeting)
    {
        this.customer = cust;
        this.supplier = supp;
        this.relationLevel = level;
        this.meetingLocation = meeting;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public String getRelationLevel()
    {
        return relationLevel;
    }

    public String getMeetingLocation()
    {
        return meetingLocation;
    }

    /**
     * Primary-Key for BusinessRelation.
     * Made up of Customer and Supplier.
     */
    public static class PK implements Serializable
    {
        public LongIdentity customer; // Use same name as field above
        public LongIdentity supplier; // Use same name as field above

        public PK()
        {
        }

        public PK(String s)
        {
            StringTokenizer st = new StringTokenizer(s, "::");
            this.customer = new LongIdentity(Customer.class, st.nextToken());
            this.supplier = new LongIdentity(Supplier.class, st.nextToken());
        }

        public String toString()
        {
            return (customer.toString() + "::" + supplier.toString());
        }

        public int hashCode()
        {
            return customer.hashCode() ^ supplier.hashCode();
        }

        public boolean equals(Object other)
        {
            if (other != null && (other instanceof PK))
            {
                PK otherPK = (PK)other;
                return this.customer.equals(otherPK.customer) && this.supplier.equals(otherPK.supplier);
            }
            return false;
        }
    }
}