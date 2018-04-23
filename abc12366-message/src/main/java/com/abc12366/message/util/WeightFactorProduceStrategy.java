package com.abc12366.message.util;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.MessageConstant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 按照权重轮询选择短信通道
 * @author zhushuai 2017-11-29
 *
 */
public class WeightFactorProduceStrategy{  
    private int i = -1; //表示上一次选择短信通道  
    private int cw = 0; //表示当前调度的权值  
    private int gcd = 0; //当前所有权重的最大公约数 比如 2，4，8 的最大公约数为：2  
    private int maxWeight;  //权重最大值
  
    private List<Integer> weights = null;  //作用计算最大公约数  
    private PartitionWeightRRParameter weightRRParametersDns[] = null; //权重配置 
    
    private static WeightFactorProduceStrategy produceStrategy = null;
  
    
    public WeightFactorProduceStrategy(List<PartitionWeightRRParameter> list) {    
        this.initWeigthParam(list);  
    }  
    
    /**
     * 单例模式实例化
     * @return
     */
    public static WeightFactorProduceStrategy getInstance(){    
        if (produceStrategy == null){
           synchronized(WeightFactorProduceStrategy.class){
                if (produceStrategy == null){
                	List<PartitionWeightRRParameter> list=new LinkedList<PartitionWeightRRParameter>();
              	    list.add(new PartitionWeightRRParameter(MessageConstant.MSG_CHANNEL_ALI,  Integer.parseInt(SpringCtxHolder.getProperty("MSG_CHANNEL_ALI"))));
         	        list.add(new PartitionWeightRRParameter(MessageConstant.MSG_CHANNEL_YOUPAI,  Integer.parseInt(SpringCtxHolder.getProperty("MSG_CHANNEL_YOUPAI"))));
         	        list.add(new PartitionWeightRRParameter(MessageConstant.MSG_CHANNEL_NETEASE, Integer.parseInt(SpringCtxHolder.getProperty("MSG_CHANNEL_NETEASE"))));
                	produceStrategy =new WeightFactorProduceStrategy(list); 
                }
                	
           }
        }
        return produceStrategy;
	}
  
  
    /**
     * 初始化权重配置信息
     * @param list
     */
    private void initWeigthParam(List<PartitionWeightRRParameter> list) {  
        weightRRParametersDns =  list.toArray(new PartitionWeightRRParameter[list.size()]);  
        weights = new ArrayList<Integer>(list.size());  
        
        for(PartitionWeightRRParameter parameter:list){
        	 weights.add(parameter.getWeight());
        }
  
        gcd = getGcdByList(weights);  
        maxWeight = getMaxWeight();  
    }  
  
    /** 
     * 计算最大公约数 
     * @param weight_m 权重数 
     * @param weight_n 权重数 
     * @return 
     */  
    private int GCD(int weight_m,int weight_n) {  
        int temp;  
        while(weight_n != 0){  
            if(weight_m < weight_n){  
                temp = weight_m;  
                weight_m = weight_n;  
                weight_n = temp;  
            }  
            temp = weight_m - weight_n;  
            weight_m = weight_n;  
            weight_n = temp;  
        }  
        return weight_m;  
    }  
  
    /** 
     * 
     * @param weights      权重列表 
     * @param startIndex   list索引值,起始位置。 
     * @param nextGcd      传入最大公约数 
     * @return 
     */  
    private int getGcdByList(List<Integer> weights, int startIndex, int nextGcd) {  
        if ( weights.size() < 2) {  
            throw new IllegalArgumentException("At least a number of parameters for 2");  
        }  
        if (weights.size() == 2 && startIndex == 0) {  
            return this.GCD(weights.get(startIndex), weights.get(startIndex + 1));  
        }  
  
        if (startIndex + 1 > weights.size() -1 )  
            return nextGcd;  
        int curGcd = nextGcd > 0 ? nextGcd : weights.get(startIndex);  
        int nextIndex = startIndex + 1;  
        nextGcd = GCD(curGcd, weights.get(startIndex + 1));              //0,1  
  
        return getGcdByList(weights, nextIndex, nextGcd);  
    }  
  
    private int getGcdByList(List<Integer> weights) {  
        return this.getGcdByList(weights, 0, 0);  
    }  
  
    private String getWeightDns() {  
        for ( ; ; ) {  
            i = (i + 1) % weightRRParametersDns.length;  
            if (i == 0) {  
                cw = cw - gcd;  //表示当前调度的权值  
                if (cw <= 0) {  
                    cw = maxWeight;  
                    if (cw == 0) {  
                        return "";  
                    }  
                }  
            }  
  
            if (weightRRParametersDns[i].getWeight() >= cw ) {  
                return weightRRParametersDns[i].getPartition();  
            }  
        }  
    }  
  
    private int getMaxWeight() {  
        int max = 0;  
        for (int i = 0; i< weightRRParametersDns.length;i++) {  
            if (weightRRParametersDns[i].getWeight() >= max) {  
                max = weightRRParametersDns[i].getWeight();  
            }  
        }  
  
        return max;  
    }  
  
    public String getPartitionIdForTopic() {  
        return this.getWeightDns();  
    }  
  
    /** 
     * 分区权重参数类 
     */  
    static class PartitionWeightRRParameter {  
        private String partition;  //通道名称
        private int weight; //权重 
  
        public PartitionWeightRRParameter(String partition, int weight) {  
            this.partition = partition;  
            this.weight = weight;  
        }  
  
        public String getPartition() {  
            return partition;  
        }  
  
        public int getWeight() {  
            return weight;  
        }  
    }  
  
} 