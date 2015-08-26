/**
 * Copyright The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hbase.group;

import org.apache.hadoop.hbase.HostPort;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface MXBean {

  public Map<String, List<HostPort>> getServersByGroup() throws IOException;

  public List<GroupInfoBean> getGroups() throws IOException;

  public static class GroupInfoBean {

    private String name;
    private List<HostPort> servers;
    private List<TableName> tables;
    private List<HostPort> offlineServers;

    //Need this to convert NavigableSet to List
    public GroupInfoBean(GroupInfo groupInfo, List<HostPort> offlineServers) {
      this.name = groupInfo.getName();
      this.offlineServers = offlineServers;
      this.servers = new LinkedList<HostPort>();
      this.servers.addAll(groupInfo.getServers());
      this.tables = new LinkedList<TableName>();
      this.tables.addAll(groupInfo.getTables());
    }

    public String getName() {
      return name;
    }

    public List<HostPort> getServers() {
      return servers;
    }

    public List<HostPort> getOfflineServers() {
      return offlineServers;
    }

    public List<TableName> getTables() {
      return tables;
    }
  }

}
