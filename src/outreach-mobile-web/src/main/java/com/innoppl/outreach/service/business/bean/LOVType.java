package com.innoppl.outreach.service.business.bean;

/**
 *
 * @author Jerald Mejarla
 */
public enum LOVType {

    addressDataQuality(1),
    disabilityType(2),
    dobDataQuality(3),
    employmentType(4),
    ethnicity(5),
    fiveValDKRefused(6),
    fysbReasonNoServices(7),
    gender(8),
    householdType(9),
    housingStatus(10),
    militaryBranch(11),
    monthsHomelessPastThreeYears(12),
    nameDataQuality(13),
    noYes(14),
    noYesRefused(15),
    pathHowConfirmed(16),
    pathSMIInformation(17),
    projectType(18),
    race(19),
    reasonNotEnrolled(20),
    referralOutcome(21),
    referralSourceSimple(22),
    relationshipToHoH(23),
    residencePrior(24),
    residencePriorLengthOfStay(25),
    ssnDataQuality(26),
    state(27),
    timesHomelessPastThreeYears(28);

    private LOVType(final int lovTypeId) {
        this.lovTypeId = lovTypeId;
    }

    /**
     * Lookup LOV Type ID by name
     *
     * @param name
     * @return
     */
    public static int getType(final String name) {
        try {
            LOVType lovType = LOVType.valueOf(name);
            if (lovType != null) {
                return lovType.lovTypeId;
            } else {
                return -1;
            }
        } catch (Exception ex) {
            return -1;
        }
    }

    private final int lovTypeId;
}
