/*
 * Microsoft JDBC Driver for SQL Server Copyright(c) Microsoft Corporation All rights reserved. This program is made
 * available under the terms of the MIT License. See the LICENSE file in the project root for more information.
 */

package com.microsoft.sqlserver.jdbc.dataclassification;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;


/**
 * Provides the functionality to retrieve Sensitivity Classification data as received from SQL Server for the active
 * resultSet
 */
public class SensitivityClassification {

    public enum SensitivityRank {
        NOT_DEFINED(-1),
        NONE(0),
        LOW(10),
        MEDIUM(20),
        HIGH(30),
        CRITICAL(40);

        private static final SensitivityRank[] VALUES = values();
        private int rank;

        private SensitivityRank(int rank) {
            this.rank = rank;
        }

        public int getValue() {
            return rank;
        }

        public static boolean isValid(int rank) throws SQLServerException {
            for (SensitivityRank r : VALUES) {
                if (r.getValue() == rank) {
                    return true;
                }
            }
            return false;
        }
    }

    private List<Label> labels;
    private List<InformationType> informationTypes;
    private List<ColumnSensitivity> columnSensitivities;
    private int sensitivityRank;

    /*
     * Creating new ArrayList here assures that 'informationTypes' and 'labels' properties will not be null. The Count
     * of the ColumnSensitivities property will be equal to the number of output columns for the query result set.
     */

    /**
     * Constructs a <code>SensitivityClassification</code> Object
     * 
     * @param labels
     *        Labels as received from SQL Server
     * @param informationTypes
     *        Information Types as received from SQL Server
     * @param columnSensitivity
     *        Column Sensitivities as received from SQL Server
     */
    public SensitivityClassification(List<Label> labels, List<InformationType> informationTypes,
            List<ColumnSensitivity> columnSensitivity) {
        this.labels = new ArrayList<>(labels);
        this.informationTypes = new ArrayList<>(informationTypes);
        this.columnSensitivities = new ArrayList<>(columnSensitivity);
    }

    /**
     * Constructs a <code>SensitivityClassification</code> Object
     * 
     * @param labels
     *        Labels as received from SQL Server
     * @param informationTypes
     *        Information Types as received from SQL Server
     * @param columnSensitivity
     *        Column Sensitivities as received from SQL Server
     * @param sensitivityRank
     *        Sensitivity rank as received from SQL Server
     */
    public SensitivityClassification(List<Label> labels, List<InformationType> informationTypes,
            List<ColumnSensitivity> columnSensitivity, int sensitivityRank) {
        this.labels = new ArrayList<>(labels);
        this.informationTypes = new ArrayList<>(informationTypes);
        this.columnSensitivities = new ArrayList<>(columnSensitivity);
        this.sensitivityRank = sensitivityRank;
    }

    /**
     * Returns the labels for this <code>SensitivityClassification</code> Object
     * 
     * @return labels
     */
    public List<Label> getLabels() {
        return labels;
    }

    /**
     * Returns the information types for this <code>SensitivityClassification</code> Object
     * 
     * @return informationTypes
     */
    public List<InformationType> getInformationTypes() {
        return informationTypes;
    }

    /**
     * Returns the column sensitivity for this <code>SensitivityClassification</code> Object
     * 
     * @return columnSensitivities
     */
    public List<ColumnSensitivity> getColumnSensitivities() {
        return columnSensitivities;
    }

    public int getSensitivityRank() {
        return sensitivityRank;
    }
}
