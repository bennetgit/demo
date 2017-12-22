/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spring.demo.health;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.remoting.zookeeper.ZookeeperClient;
import com.alibaba.dubbo.remoting.zookeeper.zkclient.ZkclientZookeeperClient;
import com.opentrans.otms.zookeeper.CustomZookeeperClient;
import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of a {@link HealthIndicator} returning status
 * information for Redis data stores.
 *
 * @author Christian Dupuis
 * @author Richard Santana
 * @since 1.1.0
 */
public class ZookeeperHealthIndicator extends AbstractHealthIndicator {

    private static final int TIME_OUT = 10000;

    private String name;
    private String protocol;
    private String address;

    public ZookeeperHealthIndicator(String name, String protocol, String address) {
        this.name = name;
        this.protocol = protocol;
        this.address = address;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        List<URL> urls = getUrls(address);
        ZookeeperClient zookeeperClient = null;

        if (CollectionUtils.isEmpty(urls)) {
            builder.unknown();
            return;
        }

        for (URL url : urls) {
            try {
                zookeeperClient = new CustomZookeeperClient(url, TIME_OUT);
                builder.up();
                break;
            } finally {
                if (zookeeperClient != null) {
                    zookeeperClient.close();
                }
            }
        }

    }

    @Override
    public String getName() {
        return name;
    }

    private URL getUrl(String address) {

        if (!address.contains("zookeeper://")) {
            return null;
        }

        String surl = address.split("//")[1];
        if (StringUtils.isBlank(surl)) {
            return null;
        }

        String[] urlSplit = surl.split(":");
        String host = urlSplit[0];
        String sport = urlSplit[1];
        try {
            Integer port = Integer.parseInt(sport);
            URL url = new URL(this.protocol, host, port);
            return url;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private List<URL> getUrls(String address) {

        List<URL> urls = new ArrayList<URL>();

        if (!StringUtils.isBlank(address)) {
            String[] addrs = address.split(";");

            URL url;
            for (String addr : addrs) {
                url = getUrl(addr);
                if (url != null) {
                    urls.add(getUrl(addr));
                }
            }
        }

        return urls;
    }

}
